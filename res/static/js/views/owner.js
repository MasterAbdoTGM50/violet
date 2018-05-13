var OwnerView = Backbone.View.extend({

    initialize: function(options) {
        this.listenTo(this.model, "sync change", this.render);
        this.template = _.template($("#nav-storeOwner-template").html());
    },

    render: function() {
        this.$el.find("#nav").html(this.template(this.model.attributes));
        return this;
    }

});
