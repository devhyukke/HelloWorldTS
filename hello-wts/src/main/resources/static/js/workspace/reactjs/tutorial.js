var data = [
    {id: 1, author: "Pete Hunt", text: "This is one comment"},
    {id: 2, author: "Jordan Walke", text: "This is *another* comment"}
];

var Comment = React.createClass({
    rawMarkup: function() {
        var md = new Remarkable();
        var rawMarkup = md.render(this.props.children.toString());
        return { __html: rawMarkup };
    },
    render: function() {
        var md = new Remarkable();
//      {this.props.children}
//      {md.render(this.props.children.toString())}
        return (
            <div className="comment">
              <h2 className="commentAuthor">
                {this.props.author}
              </h2>
              <span dangerouslySetInnerHTML={this.rawMarkup()} />
            </div>
        );
    }
});

var CommentList = React.createClass({
    render: function() {
        // Hello, world! I am a CommentList.
//        <Comment author="Pete Hunt"> This is one comment</Comment>
//        <Comment author="Jordan Walke">This is *another* comment</Comment>
        var commentNodes = this.props.data.map(function(comment) {
            return (
                <Comment author={comment.author} key={comment.id}>
                  {comment.text}
                </Comment>
            );
        });
        return (
            <div className="commentList">
              {commentNodes}
            </div>
        );
    }
});

var CommentForm = React.createClass({
    render: function() {
        return (
            <div className="commentForm">
              Hello, world! I am a CommentForm.
            </div>
        );
    }
});

var CommentBox = React.createClass({
    render: function() {
        // Hello, world! I am a CommentBox.
        return (
            <div className="commentBox">
              <h1>Comments</h1>
              <CommentList data={this.props.data} />
              <CommentForm />
            </div>
        );
    }
});
ReactDOM.render(
    <CommentBox data={data} />,
    document.getElementById('content')
);
