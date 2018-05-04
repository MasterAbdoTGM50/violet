var PageView = Backbone.View.extend({

    initialize: function() { this.template = _.template($("#page-template").html()); },

    render: function() {
        this.$el.html(this.template());
        return this;
    }

});
