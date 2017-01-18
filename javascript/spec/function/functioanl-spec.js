describe('functional approach', function () {
    let numbers;

    beforeEach(function () {
        numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    });

    it('array filter', function () {
        let evenNumbers = numbers.filter(number => number % 2 === 0);

        expect(evenNumbers).toEqual([2, 4, 6, 8, 10]);
    });

    it('array map', function () {
        let squareNumbers = numbers.map(number => number * number);

        expect(squareNumbers).toEqual([1, 4, 9, 16, 25, 36, 49, 64, 81, 100]);
    });

    it('array forEach', function () {
        let message = "";
        numbers.forEach((number, idx) => {
                if (number === 7)
                    message = message.concat("Find 7 at " + idx + "th of array.");
            }
        );

        expect(message).toBe("Find 7 at 6th of array.")
    });

    it('array reduce', function () {
        let multiply = numbers.reduce((pre, cur) => pre * cur, 1);

        expect(multiply).toBe(3628800);
    });
});