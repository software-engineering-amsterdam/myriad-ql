/**
 * Created by Manuel on 20/02/2017.
 */


export class Type {
    constructor(location = null) {
        this.location = location;
    }

    getLocation() {
        return this.location;
    }

    accept() {
        throw new Error('Accept method should have been overwritten');
    }

    toString() {
        return this.constructor.name;
    }

    getType() {
        throw new Error('getType method should have been overwritten');
    }

    /**
     * determines whether operator can be executed on the type
     **/
    static isValidOperator() {
        throw new Error('validOperator method should have been overwritten');
    }

}

export class QLMoney extends Type {
    accept(visitor, ...params) {
        return visitor.visitMoney(this, ...params);
    }

    getType(){
        return QLMoney;
    }

    static isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'].includes(operator);
    }

    static toString(){
        return 'QLMoney';
    }

}

export class QLString extends Type {
    accept(visitor, ...params) {
        return visitor.visitString(this, ...params);
    }

    getType(){
        return QLString;
    }

    static isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '=='].includes(operator);
    }


    static toString(){
        return 'QLString';
    }
}

export class QLBoolean extends Type {
    accept(visitor, ...params) {
        return visitor.visitBoolean(this, ...params);
    }

    getType(){
        return QLBoolean;
    }

    static isValidOperator(operator){
        return ['||', '&&', '==', '!='].includes(operator);
    }

    static toString(){
        return 'QLBoolean';
    }
}

export class QLNumber extends Type {
    accept(visitor, ...params) {
        return visitor.visitNumber(this, ...params);
    }

    getType(){
        return QLNumber;
    }

    static isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'].includes(operator);
    }

    static toString(){
        return 'QLNumber';
    }
}

export class QLDate extends Type {
    accept(visitor, ...params) {
        return visitor.visitDate(this, ...params);
    }
    getType(){
        return QLDate;
    }

    static isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '=='].includes(operator);
    }

    static toString(){
        return 'QLDate';
    }
}
