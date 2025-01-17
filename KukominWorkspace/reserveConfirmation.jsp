<!-- ソースコード作成者: 浅島
修正日時: 2025/01/013
ソースコードの動作や役割: 店側予約の確認画面ではいを押すとInsert_Reserveを呼び出す
 -->

<!-- reserveConfirmation.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>予約登録確認画面</title>
    <link rel="stylesheet" href="ui/styles.css">
</head>
<body>
    <!-- ナビゲーションバー -->
    <nav>
        <a href="#">予約管理</a>
        <a href="#">マイページ</a>
        <a href="#">ログアウト</a>
    </nav>

    <!-- メインコンテンツエリア -->
    <main>
        <div class="form-container">
            <div id="reservation-id">店舗ID: ${store_id}</div>
            <div id="reserver">予約者名: ${customer_name}</div>
            <div id="date-output">予約日時: ${reservation_time}</div>
            <div id="reserver_num">予約人数: ${num_guests}</div>

            <form action="/insert_reserve" method="post">
                <input type="hidden" name="store_id" value="${store_id}">
                <input type="hidden" name="customer_name" value="${customer_name}">
                <input type="hidden" name="reservation_time" value="${reservation_time}">
                <input type="hidden" name="num_guests" value="${num_guests}">

                <h2>上記の内容でよろしいですか?</h2>
                <div class="button-group">
                    <button type="submit">はい</button> <!-- 「はい」ボタンでデータベースに登録 -->
                    <button type="button" onclick="history.back()">いいえ</button> <!-- 戻る -->
                </div>
            </form>
        </div>
    </main>
</body>
</html>
