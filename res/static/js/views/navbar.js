NavbarView = Backbone.View.extend({

    initialize: function() {
        this.template = _.template($("#nav-template").html());
        this.render();
    },

    render: function() {
        this.$el.html(this.template(this.model.attributes));
        return this;
    },

});
