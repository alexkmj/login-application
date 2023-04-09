const { Button, Typography, AppBar, Toolbar } = MaterialUI;

class CustomAppBar extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      buttons: "",
    };
  }

  componentDidMount() {
    fetch("/api/v1/user/loggedin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.loggedin) {
          const button = <Button href="/logout">Logout</Button>;
          this.setState({ buttons: button });
        } else {
          const button = (
            <div>
              <Button href="/login">Login</Button>{" "}
              <Button href="/signup">Signup</Button>
            </div>
          );
          this.setState({ buttons: button });
        }
      });
  }

  render() {
    return (
      <AppBar
        position="static"
        sx={{ backgroundColor: "#FFFFFF", color: "#5F249F" }}
      >
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Login Application
          </Typography>
          {this.state.buttons}
        </Toolbar>
      </AppBar>
    );
  }
}

ReactDOM.render(<CustomAppBar />, document.getElementById("header"));
