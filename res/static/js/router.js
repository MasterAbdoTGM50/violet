var AppRouter = Backbone.Router.extend({

    loggedIn: false,
    username: null,
    password: null,

    view: null,
    navView: null,
    sideView: null,
    cntntView: null,

    user: null,
    storeLst: null,
    brandLst: null,
    categoryLst: null,

    loginView: null,
    pageView: null,
    adminView: null,
    storesView: null,
    storeView: null,
    addStoreView: null,
    addBrandView: null,
    addCategoryView: null,
    addProductView: null,

    routes: {

        "": "home",
        "home": "home",
        "login": "login",
        "logout": "logout",
        "admin": "admin",
        "owner": "owner",
        "stores": "stores",
        "stores/:id": "storeDetails",

        "add-store": "addStore",
        "add-brand": "addBrand",
        "add-category": "addCategory",
        "add-product": "addProduct"

    },

    initialize: function() {
        this.listenTo(this.user, "sync change", this.render);
        this.listenTo(this.storeLst, "sync change", this.render);
        this.render();
    },

    log: function() { this.loggedIn = true; },

    go: function(url) { this.navigate(url, { trigger: true }); },

    render: function() {
        if(this.view      != null) { this.view.render();      }
        if(this.navView   != null) { this.navView.render();   }
        if(this.sideView  != null) { this.sideView.render();  }
        if(this.cntntView != null) { this.cntntView.render(); }
    },

    home: function() {
        if(!this.loggedIn) { this.go("login"); }
        else {
            if     (this.user.attributes.priviliges == 0) { this.go("admin"); }
            else if(this.user.attributes.priviliges == 3) { this.go("owner"); }
            else if(this.user.attributes.priviliges == 6) { console.log("User: No Route Yet"); }
        }
    },

    login: function() {
        if(this.user == null) { this.user = new User(); }
        if(this.loginView == null) { this.loginView = new LoginView({ el: "#page", router: this, model: this.user }); }

        this.view = this.loginView;
        this.navView = null;
        this.sideView = null;
        this.cntntView = null;

        this.render();
    },

    logout:function(){
        this.loggedIn = false;
        this.go("login");
    },

    admin: function() {
        if(!this.loggedIn) { this.go("login"); }
        else {
            if(this.pageView == null) { this.pageView = new PageView({ el: $("#page") }); }
            if(this.adminView == null) { this.adminView = new AdminView({ el: $("#page"), model: this.user }); }

            if(this.storeLst == null) { this.storeLst = new StoreCollection(); }
            if(this.brandLst == null) { this.brandLst = new BrandCollection(); }
            if(this.categoryLst == null) { this.categoryLst = new CategoryCollection(); }
            if(this.storesView == null) { this.storesView = new StoreListView({  el: $("#page"), model: this.storeLst }); }

            this.storeLst.fetch();
            this.brandLst.fetch();
            this.categoryLst.fetch();

            this.view = this.pageView;
            this.navView = this.adminView;
            this.sideView = this.storesView;
            this.cntntView = null;

            this.render();
        }
    },

    owner: function() {},

    storeDetails: function(id) {
        if(!this.loggedIn) { this.go("login"); }
        else {
            if(this.storeView == null) { this.storeView = new StoreDetailsView({ el: $("#page") }); }
            this.cntntView = this.storeView;

            this.storeView.model = this.storeLst.get(id);

            this.render();
        }
    },

    addStore: function() {
        if(!this.loggedIn) { this.go("login"); }
        else {
            if(this.addStoreView == null) { this.addStoreView = new AddStoreView({ el: $("#page"), router: this }); }
            this.cntntView = this.addStoreView;
            this.render();
        }
    },

    addBrand: function() {
        if(!this.loggedIn) { this.go("login"); }
        else {
            if(this.addBrandView == null) { this.addBrandView = new AddBrandView({ el: $("#page"), router: this }); }
            this.cntntView = this.addBrandView;
            this.render();
        }
    },

    addCategory: function() {
        if(!this.loggedIn) { this.go("login"); }
        else {
            if(this.addCategoryView == null) { this.addCategoryView = new AddCategoryView({ el: $("#page"), router: this }); }
            this.cntntView = this.addCategoryView;
            this.render();
        }
    },

    addProduct: function() {
        if(!this.loggedIn) { this.go("login"); }
        else {
            if(this.addProductView == null) { this.addProductView = new AddProductView({ el: $("#page"), router: this, brandLst: this.brandLst, categoryLst: this.categoryLst }); }
            this.cntntView = this.addProductView;
            this.render();
        }
    }

});
