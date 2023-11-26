$(document).ready(function () {
    var headShortUrl = ""; // Переменная для хранения текущего shortUrl

    setInterval(function () {
        try {
            updateClickCounter(headShortUrl);
        } catch (error) {
            console.error("Ошибка при выполнении updateClickCounter:", error);
        }
    }, 2000);

    $("#shortenBtn").click(function (event) {
        // Предотвращаем стандартное поведение формы
        event.preventDefault();

        var urlValue = $("#urlInput").val();

        $.ajax({
            url: "/short",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({ url: urlValue }),
        })
            .done(function (data) {
                var additionalText = "localhost:8080/my/";
                if (data.shortUrl === 'Not Found') {
                    additionalText = "";
                }
                headShortUrl = data.shortUrl;
                var shortUrl = additionalText + data.shortUrl; // Обновляем shortUrl

                // Обновляем интерфейс
                $("#shortenedUrl").html(`
                <p><strong>Сокращенная ссылка:</strong></p>
                <input type="text" value="${shortUrl}" id="shortenedInput" readonly>
                <button id="copyButton" onclick="copyToClipboard()">Копировать</button>
                <span id="copySuccess">Скопировано!</span>
                <div id="clickCounter">Переходов: <span id="counter">${data.countClick}</span></div>`);
            });
    });

    function updateClickCounter(url) {
        $.ajax({
            url: "/getClickCount/" + url,
            type: "GET",
        })
            .done(function (count) {
                $("#counter").text(count);
            });
    }
});

function copyToClipboard() {
    var input = document.getElementById("shortenedInput");
    input.select();
    document.execCommand("copy");

    // Показываем сообщение об успешном копировании
    var copySuccess = document.getElementById("copySuccess");
    copySuccess.style.display = "inline";

    // Скрываем сообщение об успешном копировании через некоторое время
    setTimeout(function() {
        copySuccess.style.display = "none";
    }, 1000);
}
