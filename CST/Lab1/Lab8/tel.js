var currentEntry = "";
var currentObj = {};

$(document).ready(function () {
    // Run the start-up routine, which, in this case, loads the current list of entries from
    // local storage and displays them on the main (list) page...
    init();

    // Now install the event handlers for buttons the user can click or tap on.
    // 1. The "Add" button (for adding a new entry)...
    $("#add").click(function () {
        $("#name").text("New Entry");
        currentEntry = "";
        var e = new Entry();    // An empty one.
        displayEntry(e);
    });

    // 2. The "Del" button, for deleting an entry...
    $("#del").click(function () {
        if (currentEntry !== "") {
            removeEntry(currentEntry);
            currentEntry = "";
            displayEntryList("#list");
            saveList();
            location.href = "#";
        }
    });

    // 3. The "Update" button, for updating an entry's details...
    $("#update").click(function () {
        if (currentEntry === "") {
            addNewEntry();
        } else {
            updateEntry();
        }
        displayEntryList("#list");
        // Whenever anything is changed, save the whole list...
        saveList();
    });
    // $(function () {
    //     $('#profile_image').change(function (e) {
    //         var img = URL.createObjectURL(e.target.files[0]);
    //         $('#image').attr('src', img);
    //     });
    // });

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                // localStorage.setItem('img', e.target.result);
                currentObj.img = e.target.result;
                $('#image').attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#profile_image").change(function () {
        readURL(this);
    });

    $("#profile_image").on('click', function () {
        $(".file-upload").click();
    });

    console.log("done");

});

// This selector applies to all <a> elements inside the <ul> with the id "list".
// $(this) is a jQuery object referencing the actual <a> element that was clicked on.
$(document).on('click', "#list a", function () {
    currentEntry = this.querySelector("h2").textContent;
    // currentEntry = $(this).text();                  // The text in the <a> element, which is an Entry's displayName()
    console.log(currentEntry);
    var e = getEntryFromDisplayName(currentEntry);  // This get a reference to the actual Entry
    console.log(e);
    displayEntry(e);                                // This puts it into the form on the 'entry' page
});

// This gets call when the app is first loaded...
function init() {
    loadList();
    displayEntryList("#list");
}

var Entry = function (name, mobile, email, img, gender) {
    this.name = name;
    this.mobile = mobile;
    this.email = email;
    this.img = img;
    this.gender = gender;
}

Entry.prototype.displayName = function () {
    var firstnames, surname;
    firstnames = this.name.substring(0, this.name.lastIndexOf(" ")).trim();
    surname = this.name.substring(this.name.lastIndexOf(" ") + 1);
    return surname + ", " + firstnames;
}


Entry.prototype.changeName = function (firstnames, surname) {
    this.name = firstnames.trim() + " " + surname.trim();
    return this;
}

var entries = [];		// Start with a simple array


function addEntry(name, mobile, email, img, gender) {
    var e = new Entry(name, mobile, email, img, gender);
    entries.push(e);
    sortEntries();
    return e;
}

function removeEntry(name) {
    var pos = -1, index, entry = null;
    for (index = 0; index < entries.length; index += 1) {
        if (name === entries[index].displayName()) {
            pos = index;
            break;
        }
    }
    if (pos > -1) {
        entry = entries[pos];
        entries.splice(pos, 1);
    }
    return entry;
}

function deleteIT() {
    if (currentEntry !== "") {
        removeEntry(currentEntry);
        currentEntry = "";
        displayEntryList("#list");
        saveList();
    }
}

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
                        <h2>${entries[index].displayName()}</h2>
                        <p>${entries[index].mobile}</p>
                    </a>
                </li>
        `;


        // "<li><a href='#entry'>" + entries[index].displayName() + "</a></li>"; // name='item'
    }
    return list;
}

function displayEntryList(listElement) {
    $(listElement).html(entryList()).listview('refresh');
    return $(listElement);
}

function getEntryFromDisplayName(displayName) {
    var index, e;
    for (index = 0; index < entries.length; index += 1) {
        if (entries[index].displayName() === displayName) {
            return entries[index];
        }
    }
    return null;
}

function displayEntry(e) {
    currentObj = e;
    $("#fullname").val(e.name);
    $("#mobile").val(e.mobile);
    $("#mobilebutton").attr("href", "tel:" + e.mobile);
    $("#email").val(e.email);
    $("#mailbutton").attr("href", "mailto:" + e.email);
    // $("#img").val(e.img);
    $("#profile_image").val("");
    // $('#image').attr('src', "data:image/png;base64," + e.img);
    // $('#image').attr('src', e.img);
    $('#image').attr('src', e.img);
    $("input[name=gender-choice][value=" + e.gender + "]").prop('checked', true);
    $("input[name=gender-choice]").checkboxradio("refresh");
    $("#name").text(e.name);
}

function updateEntry() {
    var e = getEntryFromDisplayName(currentEntry);
    e.name = $("#fullname").val();
    e.mobile = $("#mobile").val();
    e.email = $("#email").val();
    e.img = currentObj.img;
    // e.img = $('#image').attr('src');
    e.gender = $("input[name='gender-choice']:checked")[0].value;
}

function addNewEntry() {
    var name = $("#fullname").val(),
        mobile = $("#mobile").val(),
        email = $("#email").val(),
        // img = $('#image').attr('src'),
        img = currentObj.img,
        // img = getBase64Image($('#image')),
        gender = $("input[name='gender-choice']:checked")[0].value;

    if (name !== "") {
        return addEntry(name, mobile, email, img, gender);
    } else {
        return null;
    }
}

function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;

    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0);

    var dataURL = canvas.toDataURL("image/png");

    return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
}

function saveList() {
    localStorage.phoneBook = JSON.stringify(entries);
}

function loadList() {
    var strList;
    strList = localStorage.phoneBook;
    if (strList) {
        entries = JSON.parse(strList);
        var proto = new Entry();
        for (e in entries) {
            entries[e].__proto__ = proto;
        }
    } else {
        entries = [];
    }
}