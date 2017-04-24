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
        return this.constructor;
    }

    /**
     * determines whether operator can be executed on the type
     **/
    isValidOperator(operator) {
        throw new Error('validOperator method should have been overwritten');
    }

}

export class QLMoney extends Type {
    accept(visitor, ...params) {
        return visitor.visitMoney(this, ...params);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'].includes(operator);
    }

}

export class QLString extends Type {
    accept(visitor, ...params) {
        return visitor.visitString(this, ...params);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '=='].includes(operator);
    }
}

export class QLBoolean extends Type {
    accept(visitor, ...params) {
        return visitor.visitBoolean(this, ...params);
    }

    isValidOperator(operator){
        return ['||', '&&', '==', '!='].includes(operator);
    }
}

export class QLNumber extends Type {
    accept(visitor, ...params) {
        return visitor.visitNumber(this, ...params);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '==', '*', '/', '+', '-'].includes(operator);
    }
}

export class QLDate extends Type {
    accept(visitor, ...params) {
        return visitor.visitDate(this, ...params);
    }

    isValidOperator(operator){
        return ['<', '>', '>=', '<=', '!=', '=='].includes(operator);
    }
}
