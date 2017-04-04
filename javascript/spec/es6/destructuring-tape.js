const test = require('tape');

test("배열 할당 - 인자의 개수가 맞을 때", function (assert) {
  let one, two, three; // for explicitness and readability;
  [one, two, three] = [1, 2, 3];

  assert.deepEqual([one, two, three], [1, 2, 3]);
  assert.end();
});

test('배열 할당 - 인자의 개수가 맞지 않을 때', function (assert) {
  let a, b, c, d;
  [a, b, c, d] = [1, 2, 3];

  assert.deepEqual([a, b, c, d], [1, 2, 3, undefined]);
  assert.end();
});

test('배열 할당 - 특정 인자 생략하고 할당하기', function (assert) {
  let a, b, c, d, other;
  [a, , , d] = [1, 2, 3, 4];
  assert.deepEqual([a, d], [1, 4]);

  [a, ...other] = [1, 2, 3, 4];
  assert.equal(a, 1);
  assert.deepEqual(other, [2, 3, 4]);
  assert.end();
});

test('객체 할당 - 기본 & 누락', function (assert) {
  let {one, two, three} = {one: 1, two: 2, nine: 9};
  assert.deepEqual([one, two, three], [1, 2, undefined]);

  assert.end();
});

test('객체 할당 - 중첩하여 할당하기', function (assert) {
  let {four: valueOfFour, five: valueOfFive} = {four: 4, five: 5};
  assert.deepEqual([valueOfFour, valueOfFive], [4, 5]);

  let {nine, plus: {ten}, eleven: value} = {nine: 9, plus: {ten: 10}, eleven: 11};
  assert.deepEqual([nine, ten, value], [9, 10, 11]);
  assert.end();
});

test('파라미터 할당', function (assert) {
  total({one: 1, two: 2});

  function total({one, two, three}) {
    assert.deepEqual([one, two, three], [1, 2, undefined]);
  }
  assert.end();
});





