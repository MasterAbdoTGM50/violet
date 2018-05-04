AddBrandView = Backbone.View.extend({

    events: {
        "click #btn-brand-add": "addBrand"
    },

    initialize: function(options) {
        _.extend(this, _.pick(options, "router"));
        this.template = _.template($("#add-brand-template").html());
    },

    render: function() {
        this.$el.find("#content").html(this.template());
        return this;
    },

    addBrand: function() {
        var _brand = new Brand({
            name: $("#brand-name").val()
        });

        _brand.credentials = {
            username: this.router.username,
            password: this.router.password
        };

        _brand.save();

        this.router.go("home");
    }

});
