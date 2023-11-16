function chBoard() {
    var btn = document.getElementsByClassName("btn")[0];
    var confirmBtn = document.getElementsByClassName("hiddenBtn")[0];
    var cancelBtn = document.getElementsByClassName("hiddenBtn")[1];
    var info = document.getElementsByClassName("userInfo");

    for (var i = 0; i < info.length; i++) {
        if (info[i].readOnly) {
            info[i].readOnly = false;
        } else {
            info[i].readOnly = true;
        }
    }

    if (btn.style.display === 'none') {
        btn.style.display = 'block';
        confirmBtn.style.display = 'none';
        cancelBtn.style.display = 'none';
    } else {
        btn.style.display = 'none';
        confirmBtn.style.display = 'block';
        cancelBtn.style.display = 'block';
    }
}

// 초기 화면 로드 시
document.addEventListener("DOMContentLoaded", function () {
    var confirmBtn = document.getElementsByClassName("hiddenBtn")[0];
    var cancelBtn = document.getElementsByClassName("hiddenBtn")[1];

    // 초기 화면에서 "확인" 및 "취소" 버튼 숨기기
    confirmBtn.style.display = 'none';
    cancelBtn.style.display = 'none';
});