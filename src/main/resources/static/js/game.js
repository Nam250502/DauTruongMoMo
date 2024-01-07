var playerScore = 0;
var opponentScore = 0;

function submitAnswer() {
    var answer = $("#answer").val();
    // Gửi câu trả lời đến máy chủ để xử lý
    // (sử dụng WebSocket hoặc AJAX)
    // Sau đó cập nhật điểm số và câu hỏi mới
    updateScore();
    showNewQuestion();
}

function updateScore() {
    // Cập nhật điểm số và hiển thị trên giao diện
    playerScore++;
    $("#player1Score").text("Player 1: " + playerScore);
}

function showNewQuestion() {
    // Lấy câu hỏi mới từ máy chủ và hiển thị trên giao diện
    var newQuestion = "This is a new question...";
    $("#questionText").text(newQuestion);
}