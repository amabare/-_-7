<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>座席登録確認</title>
</head>
<body>
    <h2>座席登録内容の確認</h2>
    <p><strong>席名:</strong> ${seat_name}</p>
    <p><strong>収容人数:</strong> ${capacity}</p>
    <p><strong>店舗ID:</strong> ${store_id}</p>

    <form action="/insert_seat_registration" method="post">
        <!-- 隠しフィールドで登録情報を再送信 -->
        <input type="hidden" name="seat_name" value="${seat_name}">
        <input type="hidden" name="capacity" value="${capacity}">
        <input type="hidden" name="store_id" value="${store_id}">

        <!-- 登録ボタン -->
        <button type="submit">登録する</button>
    </form>

    <!-- 戻るボタン -->
    <button type="button" onclick="history.back()">修正する</button>
</body>
</html>
