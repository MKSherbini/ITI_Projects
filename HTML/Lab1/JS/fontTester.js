var body = document.getElementById("body");
var content = "";
var baseFonts = ['monospace', 'sans-serif', 'serif'];
for (let i = 0; i < 6; i++) {
    var size = `font-size:${i + 2}ex`
    content += ` <p style="${size};font-family:${baseFonts[i % baseFonts.length]}">This is a paragraph.</p>`;
    content += "ali".fontsize(i + 1);
    content += document.createElement("br").outerHTML;
}
document.write(content);
