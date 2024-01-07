function registerPlayer() {
    var playerName = $("#playerName").val();
    // Gửi tên người chơi đến máy chủ để đăng ký
    // (sử dụng WebSocket hoặc AJAX)
    // Sau khi đăng ký thành công, chuyển sang trang đợi đối thủ
    $("#registration").hide();
    $("#waiting").show();
}