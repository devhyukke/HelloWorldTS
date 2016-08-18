var Todo = React.createClass({
    // TODO Doneを押下した際は取消線を入れて表示するように修正
    onComplete: function() {
        this.props.complete(this.props.data.id);
    },
    onRemove: function() {
        this.props.remove(this.props.data.id);
    },
    render: function() {
        return (
            <li className="list-group-item">{this.props.data.text}
              <button type="button" className="btn btn-sm btn-default" onClick={this.onComplete}>
                <span className="glyphicon glyphicon-ok text-success" area-hidden="true"></span> Done
              </button>
            </li>
        );
    }
});
//<button type="button" className="btn btn-sm btn-default" onClick={this.onRemove}>
//<span className="glyphicon glyphicon-remove text-danger" area-hidden="true"></span> Delete
//</button>

var TodoList = React.createClass({
    render: function() {
        var complete = this.props.complete,
            remove = this.props.remove;
        return (
            <ul className="list-group">
                {
                    this.props.data.map(function(record) {
                        if (record.completed) {
                            return;
                        }
                        return <Todo data={record} complete={complete} remove={remove} />;
                    })
                }
            </ul>
        );
    }
});
var TodoAdditionForm = React.createClass({
    onAdd: function() {
        var text = this.refs.inputText.value;
        this.props.add(text);
        this.refs.inputText.value = null;
    },
    render: function() {
        return (
            <form>
              <div className="form-group">
                <div className="input-group">
                  <input type="text" className="form-control" ref="inputText" />
                  <span className="input-group-btn">
                    <button type="button" className="btn btn-primary" onClick={this.onAdd}>Add</button>
                  </span>
                </div>
              </div>
            </form>
        );
    }
});

var Todos = React.createClass({
    add: function(text) {
        var seq = ++this.state.seq;
        var data = this.state.data.concat({
            id: seq,
            text: text,
            completed: false
        });
        this.setState({
            seq: seq,
            data: data
        });
    },
    complete: function(id) {
        var data = this.state.data;
        data.some(function(record, i){
            if (record.id==id) record.completed = true;
        });
        this.setState({
            seq: this.state.seq,
            data: data
        });
    },
    remove: function(id) {
        var data = this.state.data;
        data.some(function(v, i){
            if (v.id==id) data.splice(i,1);
        });
        this.setState({
            seq: this.state.seq,
            data: data
        });
    },
    getInitialState: function() {
        return {
            seq: 2,
            data: [{
                id: 1,
                text: "Hoge Hoge",
                completed: false
            }, {
                id: 2,
                text: "Fuga Fuga Fuga",
                completed: false
            }]
        }
    },
    render: function() {
        return (
            <section>
              <h3>Active Todos</h3>
              <TodoList data={this.state.data} complete={this.complete} remove={this.remove} />
              <div>
                <TodoAdditionForm add={this.add} />
              </div>
            </section>
        );
    }
});

var SamplePage = React.createClass({
    render: function() {
        return (
            <section>
              <h2>Sample Page <small>サンプルページ</small></h2>
              <Todos />
            </section>
        );
    }
});

var Workspace = React.createClass({
    closeWindow: function() {
        window.close();
    },
    render: function() {
        return (
            <article>
              <h1>Workspace <small>ワークスペース</small></h1>
              <SamplePage />
              <p>
                <button type="button" className="btn btn-default" onClick={this.closeWindow}>ワークスペースを閉じる</button>
              </p>
            </article>
        );
    }
});

ReactDOM.render(
    <Workspace />,
    document.getElementsByTagName('main')[0]
);
