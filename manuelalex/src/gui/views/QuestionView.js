/**
 * Created by Manuel on 25/02/2017.
 */

import {View}               from 'arva-js/core/View.js';
import {layout}             from 'arva-js/layout/Decorators.js';
import {combineOptions}     from 'arva-js/utils/CombineOptions.js';

import {Checkbox}               from 'arva-kit/components/Checkbox.js';
import {TextCaption}            from 'arva-kit/text/TextCaption.js';
import {SingleLineTextInput}    from 'arva-kit/input/SingleLineTextInput.js';


import {Money}              from '../../properties/Money.js';

export class QuestionView extends View {


    @layout.dock.top(16, 0, 10)
    label = new TextCaption({
        content: this.options.label
    });

    constructor(options = {}) {
        super(options);

        let type = options.type;
        let inputRenderable;

        switch (type.constructor) {
            case Boolean:
                inputRenderable = new Checkbox({
                    state: false,
                    enabled: true
                });
                break;
            case String:
                inputRenderable = new SingleLineTextInput({
                    required: false,
                    enabled: true,
                    usesFeedback: false,
                    type: 'text',
                    inputOptions: { clearOnEnter: true }
                });
                break;
            case Number:
                inputRenderable = new SingleLineTextInput({
                    required: false,
                    enabled: true,
                    usesFeedback: false,
                    type: 'number',
                    inputOptions: { clearOnEnter: true }
                });
                break;
            default:
                inputRenderable = new SingleLineTextInput({
                    required: false,
                    enabled: true,
                    usesFeedback: false,
                    type: 'text',
                    inputOptions: { clearOnEnter: true }
                });
                break;
        }

        this.addRenderable(inputRenderable,'input', layout.dock.top(~44,10,10));
        this.reflowRecursively();
    }
}