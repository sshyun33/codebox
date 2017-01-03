describe("array test", function () {
    var strings, numbers;

    beforeEach(function () {
        strings = ["eeeee", "dddd", "ccc", "bb", "a"];
        numbers = [5, 4, 33, 2, 1];
    });

    it("create array", function () {
        expect(strings instanceof Array).toBe(true);
    });

    it("get array length", function () {
        expect(strings.length).toBe(5);
    });

    it("get element with index", function () {
        expect(strings[1]).toBe("dddd");
    });

    it("join element", function () {
        expect(strings.join()).toBe("eeeee,dddd,ccc,bb,a");
        expect(strings.join("-")).toBe("eeeee-dddd-ccc-bb-a");
        expect(numbers.join(">")).toBe("5>4>33>2>1");
    });

    it("split string to array", function () {
        var splitted = "a b c d e".split(" ");
        expect(splitted).toEqual(["a", "b", "c", "d", "e"]);
    });

    it("push and pop", function () {
        var persons = [];

        persons.push("Lee"); // insert to last place
        expect(persons).toEqual(["Lee"]);

        persons.push("Kim");
        expect(persons).toEqual(["Lee", "Kim"]);

        expect(persons.pop()).toBe("Kim"); // get from last place and delete
        expect(persons.pop()).toBe("Lee");
    });

    it("unshift and shift", function () {
        var persons = [];

        persons.unshift("Lee"); // insert to first place
        expect(persons).toEqual(["Lee"]);

        persons.unshift("Kim");
        expect(persons).toEqual(["Kim", "Lee"]);

        expect(persons.shift()).toBe("Kim"); // get from first place and delete
        expect(persons.shift()).toBe("Lee");
    });

    it("특정한 위치에 요소 추가하거나 삭제하기", function () {
        numbers.splice(2, 0, "hi"); // (startIndex, deleteCount, element)
        expect(numbers).toEqual([5, 4, "hi", 33, 2, 1]);

        numbers.splice(2, 1, "ho", "he");
        expect(numbers).toEqual([5, 4, "ho", "he", 33, 2, 1]);

        numbers.splice(2, 2);
        expect(numbers).toEqual([5, 4, 33, 2, 1]);
    });

    it("string sorting", function () {
        strings.sort(); // natural sorting
        expect(strings).toEqual(["a", "bb", "ccc", "dddd", "eeeee"]);

        strings.sort(function (a, b) { // reverse sorting
            if (a < b) {
                return 1;
            }
            if (a > b) {
               return -1;
            }
            return 0;
            // return b.localeCompare(a); // IE11+
        });
        expect(strings).toEqual(["eeeee", "dddd", "ccc", "bb", "a"]);
    });

    it("number sorting", function () {
        numbers.sort();
        expect(numbers).toEqual([1, 2, 33, 4, 5]); // sort()는 기본적으로 문자열로 변환하여 정렬한다.

        numbers.sort(function (a, b) {
            return a - b;
        });
        expect(numbers).toEqual([1, 2, 4, 5, 33]);
    });
});