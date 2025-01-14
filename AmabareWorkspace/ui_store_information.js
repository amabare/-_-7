/*
作成者: 山本
修正日時: 2025/01/14 2303
動作や役割: 店舗の情報を表示する画面
コメント: なし
*/

// URLパラメータから店舗IDを取得する関数
function getStoreIdFromUrl() {
    const params = new URLSearchParams(window.location.search);
    return params.get('storeId');
}

// 店舗情報を表示する関数
function displayStoreInformation(store) {
    const storeInfo = document.getElementById('store-info');
    storeInfo.innerHTML = `
        <h3>${store.name}</h3>
        <p>営業時間: ${store.hours}</p>
        <p>評価: ${store.rating}</p>
        <p>空席情報: ${store.availability}</p>
        <p>住所: ${store.address}</p>
    `;
}

// サーバーから店舗情報を取得して表示する
window.onload = function() {
    const storeId = getStoreIdFromUrl();
    if (storeId) {
        fetch(`/getStoreInformation?storeId=${storeId}`)
            .then(response => response.json())
            .then(data => displayStoreInformation(data))
            .catch(error => console.error('Error:', error));
    } else {
        console.error('店舗IDが指定されていません');
    }
};