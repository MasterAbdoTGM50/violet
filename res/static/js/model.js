var User = Backbone.Model.extend({
    url: 'api/v1/users'
});

var Store = Backbone.Model.extend({
    idAttribute: "storeId",
    url: "api/v1/stores"
});

var Brand = Backbone.Model.extend({
    url: "api/v1/brands"
});

var Category = Backbone.Model.extend({
    url: "api/v1/categories"
});

var Product = Backbone.Model.extend({
    idAttribute: "productId",
    url: "api/v1/products"
});

var BrandCollection = Backbone.Collection.extend({
    model: Brand,
    url: "api/v1/brands"
});

var CategoryCollection = Backbone.Collection.extend({
    model: Brand,
    url: "api/v1/categories"
});

var StoreCollection = Backbone.Collection.extend({
    model: Store,
    url: "api/v1/stores"
});

var ProductCollection = Backbone.Collection.extend({
    model: Product,
    url: "http://localhost:8585/api/v1/products"
});