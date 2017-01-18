describe('function basic: ', function () {
    it('arguments 객체 기본 사용 테스트', function () {
        function myFunc() {
            let args, len, i;
            args = [];
            // The arguments object is a local variable available whithin all functions;
            // ex) {'0': arg1, '1': arg2, '2': arg3}
            len = arguments.length;

            for (i = 0; i < len; i++) {
                args.push(arguments[i]);
            }
            return args;
        }

        expect(myFunc()).toEqual([]);
        expect(myFunc(1, 2, 3)).toEqual([1, 2, 3]);
    });

    it('argument 객체를 Array 객체로 변환하기', function () {
        // The arguments object is not an Array.
        // but, it cat be converted to a real Array.
        function myFunc() {
            // return Array.prototype.slice.call(arguments);
            // return Array.from(arguments); // ECMA 6
            return [...arguments]; // ECMA6
        }

        expect(myFunc()).toEqual([]);
        expect(myFunc(1, 2, 3)).toEqual([1, 2, 3]);
    });
});