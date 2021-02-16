(function () {
    let cards = [
        {
            name: "php",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/php-logo_1.png",
            id: 1,
        },
        {
            name: "css3",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/css3-logo.png",
            id: 2
        },
        {
            name: "html5",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/html5-logo.png",
            id: 3
        },
        {
            name: "jquery",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/jquery-logo.png",
            id: 4
        },
        {
            name: "javascript",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/js-logo.png",
            id: 5
        },
        {
            name: "node",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/nodejs-logo.png",
            id: 6
        },
        {
            name: "photoshop",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/photoshop-logo.png",
            id: 7
        },
        {
            name: "python",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/python-logo.png",
            id: 8
        },
        {
            name: "rails",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/rails-logo.png",
            id: 9
        },
        {
            name: "sass",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/sass-logo.png",
            id: 10
        },
        {
            name: "sublime",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/sublime-logo.png",
            id: 11
        },
        {
            name: "wordpress",
            img: "https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/wordpress-logo.png",
            id: 12
        },
    ];
    let shuffle = function (array) {
        var counter = array.length, temp, index;
        // While there are elements in the array
        while (counter > 0) {
            // Pick a random index
            index = Math.floor(Math.random() * counter);
            // Decrease counter by 1
            counter--;
            // And swap the last element with it
            temp = array[counter];
            array[counter] = array[index];
            array[index] = temp;
        }
        return array;
    }

    let backImage = 'https://s3-us-west-2.amazonaws.com/s.cdpn.io/74196/codepen-logo.png';

    var el = function (element) {
        if (element.charAt(0) === "#") { // If passed an ID...
            return document.querySelector(element); // ... returns single element
        }
        return document.querySelectorAll(element); // Otherwise, returns a nodelist
    };

    let gameLen = 3;
    cards = cards.slice(0, gameLen);
    cards = [...cards, ...cards];
    cards = shuffle(cards);
    let container = el('#game-container');
    let html = '';
    cards.forEach(value => {
        html += `<div class="memory-card" data-framework="${value.name}">
            <img class="front-face" src="${value.img}" alt="${value.name}"/>
            <img class="back-face" src="${backImage}" alt="Back Badge"/>
        </div>`;
    });
    container.innerHTML = html;
    cards = document.querySelectorAll('.memory-card');
    cards.forEach(card => card.style.flexBasis = `${100 / (1 + Math.sqrt(gameLen * 2))}%`);
    let hasFlippedCard = false;
    let lockBoard = false;
    let firstCard, secondCard;
    let score = 0;
    let timer = el('#timer');
    let time = 0;
    let timerFun;

    function flipCard() {
        if (lockBoard) return;
        if (this === firstCard) return;

        this.classList.add('flip');

        if (!hasFlippedCard) {
            hasFlippedCard = true;
            firstCard = this;

            return;
        }

        secondCard = this;
        checkForMatch();
    }

    function checkForMatch() {
        let isMatch = firstCard.dataset.framework === secondCard.dataset.framework;

        isMatch ? disableCards() : unflipCards();
    }

    function disableCards() {
        firstCard.removeEventListener('click', flipCard);
        secondCard.removeEventListener('click', flipCard);

        resetBoard();
        scoreCards();
    }

    function scoreCards() {
        score++;
        if (score === gameLen)
            setTimeout(() => {
                alert(`U won in ${time} seconds`);
                stopTimer();

            }, 500);
    }

    function unflipCards() {
        lockBoard = true;

        setTimeout(() => {
            firstCard.classList.remove('flip');
            secondCard.classList.remove('flip');

            resetBoard();
        }, 500);
    }

    function resetBoard() {
        [hasFlippedCard, lockBoard] = [false, false];
        [firstCard, secondCard] = [null, null];
    }


    function startTimer() {
        timer.innerHTML = time;
        timerFun = setInterval(function () {
            time++;
            timer.innerHTML = time;
        }, 1000);
    }

    function stopTimer() {
        clearInterval(timerFun);
    }

    // (function shuffle() {
    //     cards.forEach(card => {
    //         let randomPos = Math.floor(Math.random() * 12);
    //         card.style.order = randomPos;
    //     });
    // })();

    cards.forEach(card => card.addEventListener('click', flipCard));
    startTimer();
})();