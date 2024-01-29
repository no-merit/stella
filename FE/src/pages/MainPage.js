import React from "react";
import { Route, Routes } from "react-router-dom";

// import Login from '../components/login/Login'
// import Regist from '../components/login/Regist'
import Sidebar from "../components/Sidebar";

import ChangeInfo from '../components/user/ChangeInfo'
import StarList from '../components/star/StarList'
import ErrorPage from "./ErrorPage";
// import StarFavoList from './star/StarFavorList'
// import FollowList from './user/FollowList'
// import FindUser from './user/FindUser'
// import StarTagSearch from './star/StarTagSearch'
// 환경설정 컴포넌트..?
// import Alarm from './user/Alarm'

//로그인 했을때 메인페이지로 온다.
export default function MainPage() {
        return (
        <div className="MainPage">
                <div className="mainContainer">
                    <Routes>
                        <Route path="/ChangeInfo" element={<ChangeInfo/>}/>
                        <Route path="/StarList" element={<StarList/>}/>
                        <Route path="/*" element={<ErrorPage/>}/>
                    </Routes>
                </div>
                <nav className="sideContainer">
                    <Sidebar/>  
                </nav> 
        </div>
    )
    
}