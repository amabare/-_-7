<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>店舗登録確認</title>
</head>
<body>
    <h2>店舗登録内容の確認</h2>
    <p><strong>店舗名:</strong> ${store_name}</p>
    <p><strong>住所:</strong> ${store_address}</p>
    <p><strong>メールアドレス:</strong> ${store_mail}</p>
    <p><strong>電話番号:</strong> ${store_phone}</p>
    <p><strong>支払い方法:</strong> ${payment_method}</p>

    <!-- 支払い方法がクレジットカードの場合、クレジットカード情報を表示 -->
    <% if ("credit-card".equals(request.getAttribute("payment_method"))) { %>
        <p><strong>クレジットカード番号:</strong> ${card_number}</p>
        <p><strong>名義:</strong> ${card_name}</p>
        <p><strong>有効期限:</strong> ${card_expiry}</p>
        <p><strong>セキュリティコード:</strong> ${card_code}</p>
    <% } %>

    <form action="/insert_store_registration" method="post">
        <!-- 必要に応じてフォーム送信ボタンなどを追加 -->
        <button type="submit">登録する</button>
    </form>
</body>
</html>
