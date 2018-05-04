var LoginView = Backbone.View.extend({

    events: {
        "click #btn-login": "login"
    },

    initialize: function(options) {
        _.extend(this, _.pick(options, "router"));
        this.listenTo(this.model, "sync change", this.resolve);
        this.template = _.template($("#login-template").html());
        this.render();
    },

    login: function(evt) {

        this.router.username = $("#username").val();
        this.router.password = $("#password").val();

        this.model.credentials = {
                username: this.router.username,
                password: this.router.password
        };

        this.model.fetch();
    },

    render: function() {
        this.$el.html(this.template());
        return this;
    },

    resolve: function() {
        if(this.model.get("username") == this.router.username) {
            this.router.log();
            this.router.go("home");
        }
    }

});
