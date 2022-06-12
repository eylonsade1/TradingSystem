import React, { Component } from "react";
import Link from "@mui/material/Button";
import { ConnectApi } from "../API/ConnectApi";
import { ProductApi } from "../API/ProductApi";
import { Container } from "@mui/material";
import Box from "@mui/material/Box";
import { AdminApi } from "../API/AdminApi";
import { StoreApi } from "../API/StoreApi";
import Grid from "@mui/material/Grid";
import FormDialog from "./FormDialog";
import ShoppingCart from "./ShoppingCart";
import { Row } from "react-grid-system";
import HomeProductsTable from "./HomeProductsTable";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import { propSatisfies } from "ramda";


export default class HomePage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      user:this.props.user,
      username: this.props.user.name,
      stores: [],
      open_store_fields: ["Store name"],
      send_question_to_admin_fields: ["Enter your question"],
      products: [],
      snackbar: null,
    };
    this.connectAPI = new ConnectApi();
    this.productApi = new ProductApi();
    this.storeApi = new StoreApi();
    this.adminApi = new AdminApi();
    // this.logout = this.logout.bind(this);
    console.log(this.props);
    console.log("in component did mount - user state = "+this.props.user.state);
  }
  async componentDidMount() {

    // this.setState({
    //   state : this.props.user.state,
    //   username: this.props.user.name,
    // });
  }
  async open_store(values) {
    const store_name = values[0];
    let response = await this.storeApi.open_store(store_name);
    // alert(response.message);
    if (!response.was_exception) {
      const store_id = response.value;
      this.setState({ snackbar: { children: response.message, severity: "success" } });
      this.props.add_store_to_user(store_id)

    } 
    else {
      this.setState({ snackbar: { children: response.message, severity: "error" } });

    }
  }

  // async logout() {
  //   console.log("in logout home page");
  //   let response = await this.connectAPI.logout();
  //   // alert(response.message);
  //   if (!response.was_exception) {
  //     this.setState({ snackbar: { children: response.message, severity: "success" } });
  //     console.log(response)
  //     const user_logout = response.value;
  //     this.props.updateUserState(user_logout);
  //   }
  //   else
  //   {
  //     this.setState({ snackbar: { children: response.message, severity: "error" } });

  //   }
  // }

  async send_question_to_admin(values) {
    const question = values[0];
    let response = await this.adminApi.send_question_to_admin(question);
    // alert(response.message);
    if (!response.was_exception) {
      this.setState({ snackbar: { children: response.message, severity: "success" } });
    } 
    else {
      this.setState({ snackbar: { children: response.message, severity: "error" } });

    }
  }
  show_products(products) {
    console.log("in show product");
    this.setState({
      products: products,
    });
    //    return (<ShoppingCart products={products}></ShoppingCart>);
    return <ShoppingCart products={this.state.products}></ShoppingCart>;
  }
  get_notifications(){
    console.log("goti");
    let response = this.connectAPI.get_notifications("amit@gmail.com");
    // alert(response.message);
    if (!response.was_exception)
    {

        console.log("in noti, noti success!\n");
    }
    else{
        console.log("in noti, noti failed!\n");
    }
}

  render() {
    return (
      <>
        <Container>
          <Box sx={{ flexGrow: 1 }}>
            <h1 className="Header" align="center">
              Welcome To Trading System
            </h1>
            <Button onClick={()=>this.get_notifications()} varient="contained">noty</Button>
            {/* <HomePageSearch sx={{ height: '5%' }} /> */}
          </Box>
          <Grid
            container
            direction="row"
            justifyContent="center"
            alignItems="center"
          >
            <Row position="center">
              <HomeProductsTable />
            </Row>
          </Grid>
        </Container>
        <h1 style={{ color: "white" }}>-------------------------</h1>
        <h1 style={{ color: "white" }}>-------------------------</h1>
        <Grid container direction="row" justifyContent="space-evenly">
          {this.state.user.state !== 0 ? (<>
          <FormDialog
            fields={this.state.open_store_fields}
            getValues={this.open_store.bind(this)}
            name="Open Store"
          ></FormDialog>
          <FormDialog
            fields={this.state.send_question_to_admin_fields}
            getValues={this.send_question_to_admin.bind(this)}
            name="Send question to admin"
          ></FormDialog></>):null}
          {this.state.user.state === 2?<>
            <Link href="/AdminPage" underline="hover">
            {"Admin Operations"}
          </Link></>:null}
        </Grid>
        {!!this.state.snackbar && (
    <Snackbar
      open
      anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
      onClose={this.handleCloseSnackbar}
      autoHideDuration={6000}
    >
      <Alert
        {...this.state.snackbar}
        onClose={this.handleCloseSnackbar}
      />
    </Snackbar>
  )}
      </>
    );
  }
}