AddCategoryView = Backbone.View.extend({

    events: {
        "click #btn-category-add": "addCategory"
    },

    initialize: function(options) {
        _.extend(this, _.pick(options, "router"));
        this.template = _.template($("#add-category-template").html());
    },

    render: function() {
        this.$el.find("#content").html(this.template());
        return this;
    },

    addCategory: function() {
        var _category = new Category({
            name: $("#category-name").val()
        });

        _category.credentials = {
            username: this.router.username,
            password: this.router.password
        };

        _category.save();

        this.router.go("home");
    }

});
