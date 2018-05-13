AddCollaboratorView = Backbone.View.extend({

    events: {
        "click #btn-collaborator-add": "addCollaborators"
    },

    initialize: function(options) {
        _.extend(this, _.pick(options, "router", "StoreId"));
        this.listenTo(this.StoreId, "sync change", this.render);
        this.template = _.template($("#add-collaborator-template").html());
    },

    render: function() {
        this.$el.find("#content").html(this.template());
        return this;
    },

    addCollaborators: function() {

        console.log(this.StoreId);
        var AddCollaborator = Backbone.Model.extend({
            url: "http://localhost:8585/api/v1/stores/" + this.StoreId + "/collaborators"
        });

        var _collaborator = new AddCollaborator({
            username: $("#collaborator-name").val()
        });

        _collaborator.credentials = {
            username: this.router.username,
            password: this.router.password
        };

        _collaborator.save();

        this.router.go("home");
    }

});
