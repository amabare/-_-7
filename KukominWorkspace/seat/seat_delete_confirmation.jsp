<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>席情報削除確認画面</title>
    <link rel="stylesheet" href="/ui/styles.css">
</head>
<body>
    <nav>
        <a href="reservation_management">予約管理</a>
        <a href="/ui/mypage.html">マイページ</a>
        <a href="/logout">ログアウト</a>
    </nav>

    <!-- メインコンテンツエリア -->
    <main>
        <div style="padding-top: 150px">
            <!-- サーバーから受け取った seat_id を出力 -->
            <p><strong>席ID:</strong> <%= request.getAttribute("seat_id") %></p>
            <p>Seat ID: <span><%= request.getAttribute("seat_id") %></span></p>
            <p align="center">この席情報を削除します。</p>
            <p align="center">本当に削除しますか？</p>
        </div>

        <!-- 削除ボタン用フォーム -->
        <form action="/delete_seat" method="post" style="text-align: center; margin-top: 20px;">
            <!-- 削除対象の席IDを送信 -->
            <input type="hidden" name="seat_id" value="<%= request.getAttribute("seat_id") %>">
            <button type="button" onclick="history.back()">削除しない</button>
            <button type="submit">削除する</button>
        </form>
    </main>
</body>
</html>
