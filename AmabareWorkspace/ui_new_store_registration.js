/*
作成者: 山本
修正日時: 2025/01/14 22:30
動作や役割: 事業者が会員登録を行う画面で使うJavaScript
コメント: なし
*/

// 年齢の選択肢を自動生成する関数
function populateAgeOptions() {
    const ageSelect = document.getElementById('age');
    for (let i = 0; i <= 100; i++) {
        const option = document.createElement('option');
        option.value = i;
        option.textContent = i;
        ageSelect.appendChild(option);
    }
}

// ページが読み込まれたときに年齢の選択肢を生成
window.onload = populateAgeOptions;

// モーダルを開く関数
function openModal() {
    document.getElementById('termsModal').style.display = 'block';
}

// モーダルを閉じる関数
function closeModal() {
    document.getElementById('termsModal').style.display = 'none';
}

// 支払方法の変更を監視する関数
function toggleCreditCardInfo() {
    const paymentMethod = document.getElementById('payment-method').value;
    const creditCardInfo = document.getElementById('credit-card-info');
    if (paymentMethod === 'credit-card') {
        creditCardInfo.style.display = 'block';
    } else {
        creditCardInfo.style.display = 'none';
    }
}