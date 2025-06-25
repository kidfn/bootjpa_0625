console.log("boardDetail.js in");

// listBtn을 클릭하면 /board/list로 이동
document.getElementById('listBtn').addEventListener('click', ()=>{
    location.href = "/board/list"
});

// delBtn을 클릭하면 /board/remove로 이동 (bno를 가지고 가야 함 )
document.getElementById('delBtn').addEventListener('click', ()=>{
    location.href = "/board/remove?bno="+bnoValue;
});

// modBtn을 클릭하면 title, content만 readonly를 풀기
document.getElementById('modBtn').addEventListener('click', ()=>{
    document.getElementById('t').readOnly = false;
    document.getElementById('c').readOnly = false;
    
    // 실제 submit 기능을 하는 버튼 생성
    let modBtn = document.createElement("button");
    modBtn.setAttribute('type', 'submit');
    modBtn.setAttribute('id', 'regBtn');
    modBtn.classList.add('btn', 'btn-warning');
    modBtn.innerText = "Submit";
    
    // Form 태그의 가장 마지막 요소로 추가
    document.getElementById('modForm').appendChild(modBtn);
    document.getElementById('modBtn').remove();
    document.getElementById('delBtn').remove();
    
});