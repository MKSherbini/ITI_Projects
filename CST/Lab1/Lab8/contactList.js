var currentEntry = "";
var entries = []
$(document).ready(function () {

    init();

    $("#add").click(function () {
        currentEntry = "";
        var e = new Entry();
        e.displayEntry();
        console.log("click")
    });

    // $("#del").click(function () {
    //     if (currentEntry !== "") {
    //         removeEntry(currentEntry);
    //         currentEntry = "";
    //         displayEntryList("#list");
    //         saveList();
    //     }
    // });
    //
    // $("#update").click(function () {
    //     if (currentEntry === "") {
    //         addNewEntry();
    //     } else {
    //         updateEntry();
    //     }
    //     displayEntryList("#list");
    //
    //     saveList();
    // });

});

// function init() {
//     loadList();
//     displayEntryList("#list");
// }

function Entry(name, phone, email, bd, img, gender) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.bd = bd;
    this.img = img;
    this.gender = gender;
}

Entry.prototype.displayEntry = function () {
    $("#fullname").val(this.name);
    $("#mobile").val(this.phone);
    $("#email").val(this.email);
    $("#bday").val(this.bd.toISOString().substring(0, 10));
    $("#img").val(this.img);
    $("#gender-choice").val(this.gender);
    $("#mobilebutton").attr("href", "tel:" + this.phone);
    $("#mailbutton").val("href", "mailto:" + this.email);
}

function updateEntry() {
    var e = getEntryFromDisplayName(currentEntry);
    e.name = $("#fullname").val();
    e.phone = $("#mobile").val();
    e.img = $("#img").val();
    e.email = $("#email").val();
    e.bd = $("#bday").val();
    e.gender = $("#gender-choice").val();
}


// $("input[name='gender-choice']:checked")

function sortEntries() {
    entries.sort(function (a, b) {
        if (a.displayName() < b.displayName()) {
            return -1;
        }
        if (a.displayName() > b.displayName()) {
            return 1;
        }
        return 0;
    });
    return entries;
}

function entryList() {
    var index, list = "";
    for (index = 0; index < entries.length; index += 1) {
        list += `
         <li data-icon="phone"><a href="#entry">
                        <img src="${entries[index].img}">
                        <h2>${entries[index].name}</h2>
                        <p>${entries[index].phone}</p>
                    </a>
                </li>
        `
    }
    return list;
}

function getEntryFromDisplayName(displayName) {
    var index, e;
    for (index = 0; index < entries.length; index += 1) {
        if (entries[index].name === displayName) {
            return entries[index];
        }
    }
    return null;
}