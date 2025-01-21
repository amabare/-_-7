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
    <form action="/insert_store_registration" method="post">
        <p>
            <label for="store_name"><strong>店舗名:</strong></label>
            <input type="text" id="store_name" name="store_name" value="${store_name}" readonly>
        </p>
        <p>
            <label for="store_address"><strong>住所:</strong></label>
            <input type="text" id="store_address" name="store_address" value="${store_address}" readonly>
        </p>
        <p>
            <label for="store_mail"><strong>メールアドレス:</strong></label>
            <input type="email" id="store_mail" name="store_mail" value="${store_mail}" readonly>
        </p>
        <p>
            <label for="store_phone"><strong>電話番号:</strong></label>
            <input type="tel" id="store_phone" name="store_phone" value="${store_phone}" readonly>
        </p>
        <p>
            <label for="payment_method"><strong>支払い方法:</strong></label>
            <input type="text" id="payment_method" name="payment_method" value="${payment_method}" readonly>
        </p>

        <!-- 支払い方法がクレジットカードの場合、クレジットカード情報を表示 -->
        <% if ("credit-card".equals(request.getAttribute("payment_method"))) { %>
            <p>
                <label for="card_number"><strong>クレジットカード番号:</strong></label>
                <input type="text" id="card_number" name="card_number" value="${card_number}" readonly>
            </p>
            <p>
                <label for="card_name"><strong>名義:</strong></label>
                <input type="text" id="card_name" name="card_name" value="${card_name}" readonly>
            </p>
            <p>
                <label for="card_expiry"><strong>有効期限:</strong></label>
                <input type="text" id="card_expiry" name="card_expiry" value="${card_expiry}" readonly>
            </p>
            <p>
                <label for="card_code"><strong>セキュリティコード:</strong></label>
                <input type="text" id="card_code" name="card_code" value="${card_code}" readonly>
            </p>
        <% } %>

        <button type="submit">登録する</button>
        <button class="button" type="button" onclick="history.back()">戻る</button>
    </form>
</body>
</html>
