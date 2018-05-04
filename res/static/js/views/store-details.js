var StoreDetailsView = Backbone.View.extend({

    initialize: function(options) {
        this.listenTo(this.model, 'sync change', this.render);
    	this.template = _.template($('#table-store-details').html());
	},

    render: function() {
        this.$el.find("#content").html(this.template(this.model.attributes));
        return this;
    }

});
