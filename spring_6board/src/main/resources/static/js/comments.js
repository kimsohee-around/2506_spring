/*
<li class="list-group-item d-flex">
      <span class="col-5 myfc-1">${dto.writer}</span>
      <span class="col-6">${dto.createdAt}</span>
      <span class="col-1">
        <!-- 삭제 아이콘 -->
        <i class="bi bi-trash" data-num="${dto.idx}"></i>
      </span>
    </li>
    <li class="list-group-item d-flex">
      <textarea class="form-control myfs-9" disabled>${dto.content}</textarea>
</li>
*/
console.log(username, mref)
getCommentsList()    // 함수 실행

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
  const reply = document.getElementById('replyList')
  reply.innerHTML = ''    // 초기화
  if (list && list.length > 0) {
    list.forEach(dto => {
      const li = document.createElement('li')
      str = `<li class="list-group-item d-flex">
                <span class="col-5 myfc-1">${dto.writer}</span>
                <span class="col-6">${dto.regDate}</span>
                <span class="col-1">
                  <i class="bi bi-trash" data-num="${dto.idx}"></i>
                </span>
              </li>
              <li class="list-group-item d-flex">
                <textarea class="form-control myfs-9" disabled>${dto.content}</textarea>
          </li>      
         `
      li.innerHTML = str
      reply.appendChild(li)
    });
  } else {   // list가 null 이거나 비어있으면 거짓
    reply.innerHTML = '작성된 댓글이 없습니다.'
  }

}