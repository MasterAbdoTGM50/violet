AddProductView = Backbone.View.extend({

    brandDropdownTemplate: null,
    categoryDropdownTemplate: null,

    events: {
        "click #btn-product-add": "addProduct"
    },

    initialize: function(options) {
        _.extend(this, _.pick(options, "router", "brandLst", "categoryLst"));
        this.template = _.template($("#add-product-template").html());
        this.brandDropdownTemplate = _.template($("#brand-dropdown-template").html());
        this.categoryDropdownTemplate = _.template($("#category-dropdown-template").html());
    },

    render: function() {
        this.$el.find("#content").html(this.template());

        _.each(this.brandLst.models, function(brand) {
            this.$el.find("#product-brand-list").append(this.brandDropdownTemplate(brand.attributes));
        }, this);

        _.each(this.categoryLst.models, function(category) {
            this.$el.find("#product-category-list").append(this.categoryDropdownTemplate(category.attributes));
        }, this);

        return this;
    },

    addProduct: function() {
        var _product = new Product({
            name: $("#product-name").val(),
            brand: $("#product-brand").val(),
            category: $("#product-category").val(),
            description: $("#product-description").val()
        });

        _product.credentials = {
            username: this.router.username,
            password: this.router.password
        };

        _product.save();

        this.router.go("home");
    }

});
