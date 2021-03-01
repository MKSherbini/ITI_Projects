// function f(...smt) {
//     console.log(smt);
// }
// f("a","as","as","as")

// (function () {
//     console.log("in");
// }());

// function buildFunctions() {
//
//     var arr = [];
//
//     for (let i = 0; i < 3; i++) {
//
//         arr.push(
//             function() {
//                 console.log(i);
//             }
//         )
//
//     }
//
//     return arr;
// }
//
// var fs = buildFunctions();
//
// fs[0]();
// fs[1]();
// fs[2]();

Array.prototype.smt = "s";
let a = [1, 2, 3];

for (let prop in a) {
    if (a.hasOwnProperty(prop))
        console.log(prop + " " + a[prop]);
}
