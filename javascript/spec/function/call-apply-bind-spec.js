describe("call vs apply vs bind: ", function () {

    it("call method test", function () {
        var product1 = {price: 200};
        var product2 = {price: 1500};

        function getDisccountedPrice(discount) {
            return this.price + (this.price * (discount / 100));
        }

        // call(): accept args as variable any types ('C' is a comma)
        expect(getDisccountedPrice.call(product1, 20)).toBe(240);
        expect(getDisccountedPrice.call(product2, 10)).toBe(1650);
    });

    it("apply method test", function () {
        var product1 = {price: 200};
        var product2 = {price: 1500};

        function getDisccountedPrice(discount) {
            return this.price + (this.price * (discount / 100));
        }

        // apply(): accept args as a array ('A' is a array)
        expect(getDisccountedPrice.apply(product1, [20])).toBe(240);
        expect(getDisccountedPrice.apply(product2, [10])).toBe(1650);
    });

    it("bind method test", function () {
        var product1 = {price: 200};
        var product2 = {price: 1500};

        function getDisccountedPrice(discount) {
            return this.price + (this.price * (discount / 100));
        }

        // bind(): accept args as variable any types and return a new function
        // useful to currying
        var discountedPriceOfProduct1 = getDisccountedPrice.bind(product1, 20);
        var discountedPriceOfProduct2 = getDisccountedPrice.bind(product2, 10);

        expect(discountedPriceOfProduct1()).toBe(240)
        expect(discountedPriceOfProduct2()).toBe(1650)
    });
});