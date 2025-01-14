/*
作成者: 山本
修正日時: 2025/01/14 23:09
動作や役割: 店舗の検索結果を表示する画面
コメント: なし
*/

// 検索結果の店舗情報を表示する関数
function displayStoreInformation(stores) {
    const storeList = document.getElementById('store-list');
    storeList.innerHTML = ''; // 既存の内容をクリア

    stores.forEach(store => {
        const storeDiv = document.createElement('div');
        storeDiv.className = 'store-item';
        storeDiv.innerHTML = `
            <h3>${store.name}</h3>
            <p>住所: ${store.address}</p>
            <p>営業時間: ${store.hours}</p>
            <p>タグ: ${store.tag}</p>
        `;
        storeList.appendChild(storeDiv);
    });
}

// サーバーから店舗情報を取得して表示する
window.onload = function() {
    fetch('/getStoreInformation')
        .then(response => response.json())
        .then(data => displayStoreInformation(data))
        .catch(error => console.error('Error:', error));
};