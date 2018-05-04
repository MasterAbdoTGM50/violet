var AddStoreView = Backbone.View.extend({

    events: {
        "click #btn-store-add": "addStore"
    },

    initialize: function(options) {
        _.extend(this, _.pick(options, "router"));
        this.template = _.template($("#add-store-template").html());
    },

    render: function() {
        this.$el.find("#content").html(this.template());
        return this;
    },

    addStore: function() {
        var _store = new Store({
            name: $("#store-name").val(),
            type: $("#store-type").val(),
            address: $("#store-address").val()
        });

        _store.credentials = {
            username: this.router.username,
            password: this.router.password
        };

        _store.save();

        this.router.go("home");
    }

});
