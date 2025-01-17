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
window.onload = function() {
    populateAgeOptions();

    // 支払方法の変更を監視
    document.getElementById('payment-method').addEventListener('change', toggleCreditCardInfo);
}

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
