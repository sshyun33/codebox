describe("String tets - ", function () {
    let text;

    beforeEach(function () {
        text = "This text is a sample text.";
    });

    it("문자열 길이 알아내기", function () {
        expect(text.length).toBe(27);
    });

    it("특정 위치의 문자 구하기", function () {
        expect(text.charAt(2)).toBe("i");
    });

    it("특정 문자열의 위치 찾기", function () {
        expect(text.indexOf("text")).toBe(5);
        expect(text.lastIndexOf("text")).toBe(22);
    });

    it("문자열 자르기", function () {
        expect(text.slice(5, 21)).toBe("text is a sample"); // (startIdx, EndIdx)
        expect(text.substr(5, 4)).toBe("text"); // (startIdx, length)
    });

    it("문자열 변경하기", function () {
        text = "This text is a sample text.";

        // first occurrence
        expect(text.replace("text", "word"))
            .toBe("This word is a sample text.");

        // regex
        expect(text.replace(/t..t/, "word"))
            .toBe("This word is a sample text.");

        // global occurrence
        expect(text.replace(/t..t/g, "word"))
            .toBe("This word is a sample word.");

        // ignore case
        expect(text.replace(/this/gi, "That"))
            .toBe("That text is a sample text.");

        // custom convert function
        expect(text.replace(/this|sample/gi, x => x.toUpperCase()))
            .toBe("THIS text is a SAMPLE text.");

        //group search
        expect(text.replace(/^\w{4} (text).*(sample) text\.$/, function groupConvert(match, p1, p2) {
            return "Group01: " + p1 + ", Group02: " + p2;
        })).toBe("Group01: text, Group02: sample");
    })
});
