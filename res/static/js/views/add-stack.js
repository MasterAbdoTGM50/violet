AddProductStackView = Backbone.View.extend({

    productDropdownTemplate: null,

    events: {
        "click #btn-productStack-add": "addProductStack"
    },

    initialize: function(options) {

        _.extend(this, _.pick(options, "router", "productLst", "StoreId"));
        this.listenTo(this.productLst, "sync change", this.render);
        this.listenTo(this.StoreId, "sync change", this.render);

        this.template = _.template($("#add-productStack-template").html());
        this.productDropdownTemplate = _.template($("#product-dropdown-template").html());

    },

    render: function() {

        this.$el.find("#content").html(this.template());

        _.each(this.productLst.models, function(product) {
            this.$el.find("#product-stack-list").append(this.productDropdownTemplate(product.attributes));
        }, this);

        return this;
    },

    addProductStack: function() {

        var ProductStack = Backbone.Model.extend({

            url: "http://localhost:8585/api/v1/stores/" + this.StoreId + "/products"
        });
        

        var product_name = $("#product-stack-list").val();
        var pID = null;


        _.each(this.productLst.models, function(product) {

            if(product.attributes.name == product_name){
                pID = product.attributes.productId;
            }

        }, this);


        var _productStack = new ProductStack({
            productId: pID,
            price: $("#product-price").val(),
            quantity: $("#product-quantity").val(),
        });


        _productStack.credentials = {
            username: this.router.username,
            password: this.router.password
        };

        _productStack.save();

        this.router.go("home");
    }

});