# group7_SWEngineering
グループ7がソフトウェア工学の作品の制作で使うリポジトリ

---
# GitHubの共有方法
GitHubのHOMEから共有するリポジトリをクリックする

　↓

GitHubの[Setting]から[Collaborators]を選択し、[Add People]を押す

　↓

共有したい人物のメールアドレスを入力

　↓

相手が承諾することで共有可能

---
# リポジトリをクローンする方法
ターミナル上で「cd」コマンドを使って、クローンしたいディレクトリに移動

　↓

そこで「$ git clone repo_url」を実行する ("repo_url"はレポジトリのURL)

---
# 変更をプッシュするには
以下のコマンドを順に実行する

・「$ git status」: どの変更がステージングされているかを確認

・「$ git add file_name」: 変更をステージング

・「$ git commit」: コミットを行う (このコマンドを実行した後の画面でメッセージを入力する必要がある)

・「$ git push」: 変更内容を実際に反映させる

---
# コーディング規約

全体に対する規約
---
・ファイル先頭にソースコード作成者、修正日時、ソースコードの動作や役割、ソースコードに対するコメントを複数行のコメントアウト(「/* */」等)を用いて簡潔に記述する

・デバッグ時にのみ使用したコードはテスト終了時に削除する

・インデントとしてはTabキーを用いる

・単体で300行を超えるソースコードを書かない

・100行を超えるメソッド、関数を書かない

・ファイル名は各機能を表す簡潔な英単語・数字を用いて記述する

JavaScript・Java・PHPに対する規約
---
・コメントアウトは「// 内容」のように記述する

・3行以上のコメントアウトであれば次のようにコメントアウトを記述する

　/*

　内容

　*/

・コメントアウトはそのコメントアウトに関連する箇所の直前に書く

・定数名は大文字と数字のみで記述する

・関数またはメソッドを使用する際は必ず行っている処理内容をコメントアウトにより記述する

・各演算子の間に半角スペースをつける

・","と":"、";"の後には、下記のケースを除き半角スペースを入れる

　・";"がfor文の宣言時または配列内で使用されていない

・"()"の後に"{}"を書く際はスペースを入れない

・else if文やelse文を記述するときは"}"との間にスペースを入れる

・if文による処理の記述の際は"{}"を省略しない

・関数ないしメソッドはmain関数に相当する関数、コンストラクタ後に記述する

・関数、メソッド間は1行空ける

・複数行にわたって括弧を使用する際は括弧内全体にインデントを入れる

・ファイル名はクラス名と等しくする

・クラス内変数、メソッドには必ずprivateやpublic等アクセス制御に関する修飾子をつける

・1ファイルにつきクラスは1つのみ宣言できる

・変数名、関数名、メソッド名、クラス名は下記のケースを除き3文字以上の意味のあるものとする

　・for文内でのみ使用するカウンタ

・for文内で使用するカウンタ名としては原則外側のfor文から順にi、j、k、lを用いる

・変数名の命名規則としてはキャメルケース表記を用いる

・関数名、メソッド名、クラス名の命名規則としてはパスカルケース表記を用いる

HTMLに対する規約
---
・コメントアウトは「<\!-- 内容 -->」のように記述する

・コメントアウトはそのコメントアウトに関連する箇所の直前に書く

・タグと文字の間はスペースを1個つける

・"<>"内の文字の先端と終端は"<>"に隣接させる

・ファイルの命名規則としてはスネークケース表記を用いる

CSSに対する規約
---
・コメントアウトは「/* 内容 */」のように記述し、コメントアウトはそれに関連する箇所の直前に書く

・ファイルの命名規則としてはスネークケース表記を用いる

JavaScriptに対する規約
---
・10行を超えるJavaScriptコードはHTMLファイルの外部に記述する

・ファイルの命名規則としてはパスカルケース表記を用いる

Javaに対する規約
---
・ファイルの命名規則としてはパスカルケース表記を用いる

PHPに対する規約
---
・10行を超えるPHPコードはHTMLファイルの外部に記述する

・ファイルの命名規則としてはスネークケース表記を用いる

各表記法についての補足
---
・キャメルケース表記　各単語を小文字で表記し、単語間をそのまま接続する

・パスカルケース表記　各単語の先頭を大文字、それ以外を小文字で表記し、単語間をそのまま接続する

・スネークケース表記　各単語を小文字で表記し、単語間をアンダーバーで接続する

---
