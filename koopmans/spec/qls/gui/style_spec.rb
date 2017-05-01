require 'rspec'
require 'pp'
require 'require_all'

require_all 'lib'

module QLS
  module GUI
    describe Style do
      let(:merged_style) { merged_style }

      it 'inherits from parent if not set' do
        expect(merged_style.font).to eq('Arial')
      end

      it 'does not use parent style if set' do
        expect(merged_style.width).to be(100)
      end

      def merged_style
        parent_style = Style.new
        parent_style.width = 200
        parent_style.font = 'Arial'

        style = Style.new
        style.width = 100

        style.merge_with(parent_style)
        style
      end
    end
  end
end
