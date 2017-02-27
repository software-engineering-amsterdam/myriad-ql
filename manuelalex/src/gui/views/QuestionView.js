/**
 * Created by Manuel on 25/02/2017.
 */

import {View}               from 'arva-js/core/View.js';
import {layout}             from 'arva-js/layout/Decorators.js';
import {combineOptions}     from 'arva-js/utils/CombineOptions.js';

import {Checkbox}           from 'arva-kit/components/Checkbox.js';
import {TextCaption}        from 'arva-kit/text/TextCaption.js';
import {LabeledTextInput}   from 'arva-kit/input/LabeledTextInput.js';


import {Money}              from '../../properties/Money.js';

export class QuestionView extends View {


    @layout.dock.top(16,0, 10)
    label = new TextCaption({
        content: this.options.label
    });

    constructor(options = {}) {
        super(options);

        let type = options.type;
        let renderable;

        switch(type instanceof){
            case Boolean:
                return renderable = new Checkbox({
                    enabled: true
                })
             default

        }

    }
}