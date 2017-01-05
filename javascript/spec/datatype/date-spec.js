describe('test-date: ', function () {
    let date;

    /* 1. 외부에서 로컬 시간을 지정하여도 내부적으로 UTC 시간으로 변환되어 저장된다.
       2. Month는 0(Jan)~11(Dec)로 지정한다.
       3. When setting a date, without specifying the time zone, Javascript will use browser's time zone.
       4. When getting a date, without specifying the time zone, the result is converted to the browser's time zone. */
    it('Creating date objects', function () {
        const date = new Date();
        const dateFromFormat = new Date("January 05, 2017 06:00:00");
        // ISO8601 style = yyyy-MM-ddThh:mm:ssZ (T: Separator, Z: UTC)
        const dateFromISOFormat = new Date("2017-01-05T06:00:00+0900");
        const dateFromEpoch = new Date(1483563600000);
        const dateFromArgs = new Date(2017, 0, 5, 6, 0, 0, 0);

        const expectedLocalTime = "Thu Jan 05 2017 06:00:00 GMT+0900 (KST)";
        expect(dateFromFormat.toString()).toBe(expectedLocalTime);
        expect(dateFromISOFormat.toString()).toBe(expectedLocalTime);
        expect(dateFromEpoch.toString()).toBe(expectedLocalTime);
        expect(dateFromArgs.toString()).toBe(expectedLocalTime);
    });

    it('Display date objects', function () {
        const date = new Date(2017, 0, 5, 6, 0, 0, 0);

        expect(date.toString()).toBe("Thu Jan 05 2017 06:00:00 GMT+0900 (KST)");
        expect(date.toUTCString()).toBe("Wed, 04 Jan 2017 21:00:00 GMT"); // UTC
        expect(date.toISOString()).toBe("2017-01-04T21:00:00.000Z"); // UTC in ISO8601 style(yyyy-MM-dd)
        expect(date.toDateString()).toBe("Thu Jan 05 2017");
        expect(date.toTimeString()).toBe("06:00:00 GMT+0900 (KST)");
        expect(date.getTime()).toBe(1483563600000); // Epoch time
    });


    it('Get date information', function () {
    })
});
