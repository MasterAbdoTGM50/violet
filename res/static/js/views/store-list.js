var StoreListView = Backbone.View.extend({

    initialize: function() {
        this.listenTo(this.model, "sync change", this.render);
        this.template = _.template($("#table-store-list").html());
    },

    render: function() {
        this.$el.find("#sidebar").html(this.template());
        _.each(this.model.models, function (store) {
            this.$el.find("#stores-list").append(new StoreListItemView({ model: store }).render().el);
        }, this);
        return this;
    }

});

var StoreListItemView = Backbone.View.extend({

    tagName:"li",

    initialize: function() {
        this.template = _.template($("#table-store-list-item").html());
        this.render();
    },

    render:function () {
        this.$el.html(this.template(this.model.attributes));
        return this;
    }
});
