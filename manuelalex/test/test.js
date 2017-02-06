/**
 * Created by alexvanmanen on 06-02-17.
 */
var assert = require('assert');


describe('Grammar', function() {
    describe('#1', function() {
        it('test first line of program', function() {
            assert.equal(Parser.parse("form taxOfficeExample"), "form taxOfficeExample");
        });
    });
});
