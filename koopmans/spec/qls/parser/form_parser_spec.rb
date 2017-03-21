require 'rspec'
require 'parslet/rig/rspec'
require 'require_all'

require_all 'lib'

module QLS
  module Parser
    describe FormParser do
      let(:form_parser) { FormParser.new }
      describe 'page' do
        it 'parses' do
          expect(form_parser.page).to parse('page Housing {
            section "Buying" {
              question hasBoughtHouse
            }
          }')

          expect(form_parser.page).to parse('page Housing {
            section "Buying"
              question hasBoughtHouse
                widget checkbox
            section "Loaning"
              question hasMaintLoan
          }')

          expect(form_parser.page).to parse('page Housing {
            section "Buying" {
              question hasBoughtHouse
            }
            default boolean widget radio("Yes", "No")
          }')

          expect(form_parser.page).to parse('page Housing {
            section "Selling" {
              question hasSoldHouse
                widget radio("Yes", "No")
              section "You sold a house" {
                question sellingPrice
                  widget spinbox
              }
            }
          }')
        end
      end

      describe 'section' do
        it 'parses' do
          expect(form_parser.section).to parse('section "Buying" question hasBoughtHouse widget checkbox')
          expect(form_parser.section).to parse('section "Selling" { question hasSoldHouse widget checkbox }')
          expect(form_parser.section).to parse('section "You sold a house" {
            question sellingPrice
            widget spinbox
            default money {
              width: 400
              font: "Arial"
              fontsize: 14
              color: #999999
              widget spinbox
            }
          }')
        end
      end

      describe 'question' do
        it 'parses' do
          expect(form_parser.question).to parse('question hasBoughtHouse widget checkbox')
          expect(form_parser.question).to parse('question hasMaintLoan')
          expect(form_parser.question).to parse('question hasBoughtHouse {
            widget checkbox
            width: 400
          }')
        end
      end

      describe 'widget' do
        it 'parses widget' do
          expect(form_parser.widget).to parse('widget radio("Yes", "No")')
          expect(form_parser.widget).to parse('widget checkbox')
        end

        it 'parses radio button' do
          expect(form_parser.radio).to parse('radio("Yes", "No")')
        end
      end

      describe 'default' do
        it 'parses' do
          expect(form_parser.default).to parse('default money {
            width: 400
            font: "Arial"
            fontsize: 14
            color: #999999
            widget spinbox
          }')
          expect(form_parser.default).to parse('default boolean widget radio("Yes", "No")')
        end
      end

      describe 'properties' do
        it 'parses width' do
          expect(form_parser.width).to parse('width: 400')
        end

        it 'parses font' do
          expect(form_parser.font).to parse('font: "Arial"')
        end

        it 'parses fontsize' do
          expect(form_parser.fontsize).to parse('fontsize: 14')
        end

        it 'parses color' do
          expect(form_parser.color).to parse('color: #999999')
          expect(form_parser.color).to parse('color: #999')
        end
      end

      describe 'stylesheet' do
        it 'parses' do
          expect(form_parser.stylesheet).to parse('stylesheet taxOfficeExample
            page Housing {
              section "Buying"
                question hasBoughtHouse
                  widget checkbox
              section "Loaning"
                question hasMaintLoan
            }

            page Selling {
              section "Selling" {
                question hasSoldHouse
                  widget radio("Yes", "No")
                section "You sold a house" {
                  question sellingPrice
                    widget spinbox
                  question privateDebt
                    widget spinbox
                  question valueResidue
                  default money {
                    width: 400
                    font: "Arial"
                    fontsize: 14
                    color: #999999
                    widget spinbox
                  }
                }
              }
              default boolean widget radio("Yes", "No")
            }')
        end
      end
    end
  end
end
