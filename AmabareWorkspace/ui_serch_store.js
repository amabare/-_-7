// 営業時間の選択肢を動的に生成する関数
function populateHoursOptions() {
    const hoursSelect = document.getElementById('hours');
    for (let i = 0; i < 24; i++) {
        const hour = i.toString().padStart(2, '0') + ':00～';
        const option = document.createElement('option');
        option.value = hour;
        option.textContent = hour;
        hoursSelect.appendChild(option);
    }
}

// ページが読み込まれたときに営業時間の選択肢を生成
window.onload = function() {
    populateHoursOptions();
};