console.log(username, mref)
// 전역변수
const reply = document.getElementById('replyList')     // 댓글 li 태그의 부모 ul 태그
getCommentsList()    // 함수 실행

if (username) {
  // 저장 버튼
  document.getElementById('btnSave').addEventListener('click', commentPost)   // commentSave 함수 실행
} else {
  // 로그인 버튼
  document.getElementById('btnLogin').addEventListener('click', function () {
    location.href = "/login"
  })
}


// 댓글 목록 가져오기
function getCommentsList() {
  const url = `/api/comments/${mref}`
  fetch(url)
    .then(response => {
      console.log('response: ', response)
      return response.json()    // 응답의 body json문자열을 자바스크립트 객체로 변환
    })
    .then(data => {
      console.log('data: ', data)
      printList(data)    // 응답데이터로 출력요소를 만드는 함수 실행
    })
    .catch(err => {
      console.error('error: ', err)
    })
}

function printList(list) {
  reply.innerHTML = ''    // 초기화
  if (list && list.length > 0) {
    list.forEach(dto => {
      const li = document.createElement('li')
      str = `<li class="list-group-item d-flex">
                <span class="col-5 myfc-1">${dto.writer}</span>
                <span class="col-6">${dto.regDate}</span>`

      if (dto.writer == username) {
        str += `<span class="col-1">
              <i class="bi bi-trash" data-num="${dto.idx}"></i>
            </span>`
      }
      str += `</li>
              <li class="list-group-item d-flex">
                <textarea class="form-control myfs-9" disabled>${dto.content}</textarea>
          </li>      
         `
      li.innerHTML = str
      li.style.listStyle = 'none';
      reply.appendChild(li)
    });
  } else {   // list가 null 이거나 비어있으면 거짓
    reply.innerHTML = '작성된 댓글이 없습니다.'
  }

}

// 댓글 삭제 : 위의 댓글 리스트 i 태그에게 이벤트 등록 필요.
// ㄴ 참고 const reply = document.getElementById('replyList')   // 위에서 이미 전역 변수 선언
reply.addEventListener('click', function (event) {
  console.log('이벤트 요소 :', event.target.tagName)
  if (event.target.tagName === 'I') {
    const idx = event.target.getAttribute('data-num');
    if (confirm('댓글을 삭제하시겠습니까?')) {
      commentDelete(idx)     // 댓글 삭제 요청 함수
    }
  }
})

function commentDelete(idx) {
  const url = `/api/comments/${idx}/${mref}`
  const options = { method: 'DELETE' }
  fetch(url, options)
    .then(response => { return response.json() })
    .then(data => {
      if (data.success === 1)
        alert('댓글 삭제했습니다.')
    })
    .then((() => getCommentsList()))    //변경된 댓글 목록 요청
    .catch(err => console.error(err))
}

// 댓글 추가
/*
{
   "mref" : 298,
   "writer" : "wonder",
   "content" : "좋은 글이네요.👍"
}
*/
function commentSave() {
  const url = `/api/comments`
  const newReply = {   // 새로 작성한 댓글
    mref: mref,
    writer: username,
    content: document.getElementById('content').value
  }
  const options = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    body: JSON.stringify(newReply)
  }   // newReply 자바스트립트 오브젝트를 body 에는 json 문자열로 변환하여 보내기

  fetch(url, options)
    .then(response => response.json())
    .then(data => {
      if (data.success === 1) {
        alert('댓글이 등록 되었습니다.')
        document.getElementById('content').value = ''
      }
    })
    .then(() => getCommentsList())  //변경된 댓글 목록 요청
    .catch(err => console.error(err))
}

// fetch 의 다른 형식 함수 - commentSave 와 같이 하면 중첩구조가 생깁니다.
async function commentPost() {
  const url = `/api/comments`
  const newReply = {   // 새로 작성한 댓글
    mref: mref,
    writer: username,
    content: document.getElementById('content').value
  }
  const options = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json;charset=UTF-8' },
    body: JSON.stringify(newReply)
  }
  try {
    const response = await fetch(url, options);   // 비동기 함수 처리 await

    if (response.status === 400) {
      const errorMap = await response.json();
      for (const [field, message] of Object.entries(errorMap)) {
        const errorMsg = document.getElementById(`${field}-error`);
        if (errorMsg) errorMsg.innerHTML = message;
      }
      return;
    }

    if (!response.ok) {
      throw new Error(`서버 오류: ${response.status}`);
    }

    const data = await response.json();   // 정상 실행인 경우
    if (data.success === 1) {
      document.querySelectorAll('.errorMsg').forEach(ele => ele.innerHTML = '')   // 에러 메시지 지우기
      document.getElementById('content').value = ''
      alert('댓글이 등록 되었습니다.');
      getCommentsList();
    }
  } catch (err) {
    console.error(err);
    alert("댓글 등록 중 오류가 발생했습니다.");
  }
}
/*
SSR    :    컨트롤러 + view (백엔드 타임리프)
              유효성 검사는 자바스크립트   

CSR    :    API 컨트롤러 + 프론트엔드 React
 유효성 검사는 스프링 Validation 기능을 사용하자
 ㄴ 오류 메시지는 스프링이 보내주고, 화면 출력은 JS(또는 리액트)

 */